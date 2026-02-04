'use client'

import { useMutation, useQuery } from '@tanstack/react-query'
import { format } from 'date-fns'
import { ChevronsUpDown, MessageSquare, Pencil, PenSquare, Trash2, X } from 'lucide-react'
import { useSession } from 'next-auth/react'
import { FC, useEffect, useState } from 'react'
import { Controller, useForm, useWatch } from 'react-hook-form'
import { toast } from 'react-toastify'

import { PaginationProps, SortingProps } from '@/app/_models'
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import { Button } from '@/components/ui/button'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { ScrollArea } from '@/components/ui/scroll-area'
import { Separator } from '@/components/ui/separator'
import { Spinner } from '@/components/ui/sprinner'
import { StarRating } from '@/components/ui/star-rating'
import { Textarea } from '@/components/ui/textarea'
import { DATETIME_FORMATS } from '@/lib/constants'
import { CustomerHelper, ProductHelper, SessionHelper } from '@/lib/helpers'
import { extractInitials } from '@/lib/utils'
import { Feedback, FeedbackExtended, ProductExtended, Session } from '@/models'
import { requestCreateFeedback, requestFeedbacksByProductId } from '@/repositories/consul'
import { FETCH_FEEDBACK_BY_PRODUCT_ID_WITH_PAGINATION_SORTING } from '@/repositories/query-keys'

import { PaginationControlsClient } from '../../_components'
import { useProfile } from '@/hooks'
import { AlertDialog, AlertDialogAction, AlertDialogCancel, AlertDialogContent, AlertDialogDescription, AlertDialogFooter, AlertDialogHeader, AlertDialogTitle } from '@/components/ui/alert-dialog'

type Props = {
  product: ProductExtended
  totalFeedbacks: number
  averageStar: number
}

type FeedbackFormProps = {
  rating: number
  content: string
}

const INIT_PAGINATION_OBJ: PaginationProps = {
  totalItems: 0,
  totalPages: 0,
  currentPage: 1,
  pageSize: 10,
}

const INIT_SORTING_OBJ: SortingProps = {
  field: 'star',
  order: 'DESC',
}

export const ProductFeedback: FC<Props> = ({ product, averageStar, totalFeedbacks }) => {
  const session = useSession()
  const sessionHelper = new SessionHelper(session)
  const productHelper = new ProductHelper(product)
  const [paginationObj, setPaginationObj] = useState<PaginationProps>(INIT_PAGINATION_OBJ)
  const [sortingObj, setSortingObj] = useState<SortingProps>(INIT_SORTING_OBJ)
  const [feedbackList, setFeedbackList] = useState<FeedbackExtended[]>([])
  const { data: feedbackResponse, isSuccess } = useQuery({
    queryKey: FETCH_FEEDBACK_BY_PRODUCT_ID_WITH_PAGINATION_SORTING(
      productHelper.id,
      paginationObj.currentPage - 1,
      paginationObj.pageSize,
      sortingObj.field,
      sortingObj.order,
    ),
    queryFn: () =>
      requestFeedbacksByProductId({
        productId: productHelper.id,
        pagination: { offset: paginationObj.currentPage - 1, limit: paginationObj.pageSize },
        sorting: { field: sortingObj.field, order: sortingObj.order },
      }),
  })
  useEffect(() => {
    if (isSuccess) {
      const { data, totalPages, total } = feedbackResponse
      setFeedbackList(data)
      setPaginationObj({
        ...paginationObj,
        totalPages: totalPages,
        totalItems: total,
      })
    }
  }, [feedbackResponse, isSuccess])
  const [showForm, setShowForm] = useState(false)
  const {
    control,
    handleSubmit,
    reset,
    register,
    formState: { isSubmitting },
  } = useForm<FeedbackFormProps>({
    defaultValues: { rating: 0, content: '' },
    mode: 'onSubmit',
  })
  const ratingValue = useWatch({ control, name: 'rating' })
  const contentValue = useWatch({ control, name: 'content' })
  const { mutate: createFeedbackMutate } = useMutation({
    mutationFn: async (payload: {
      session: Session
      productId: string
      star: number
      content: string
    }) => requestCreateFeedback(payload),
    onSuccess: () => {
      setShowForm(false)
      reset()
      toast.success('Thank you for your review!')
    },
  })
  const [editingFeedback, setEditingFeedback] = useState<Feedback | null>(null)
  const [feedbackToDelete, setFeedbackToDelete] = useState<string | null>(null)

  const onPageChange = (page: number) => {
    setPaginationObj({
      ...paginationObj,
      currentPage: page,
    })
  }

  const onFeedbackSubmit = ({ rating, content }: FeedbackFormProps) => {
    if (rating === 0 || !content.trim()) {
      toast.error('Please provide a rating and a comment.')
      return
    }
    createFeedbackMutate({
      session: sessionHelper.dataClient(),
      productId: productHelper.id,
      star: rating,
      content,
    })
  }

  const getSortLabel = () => {
    if (sortingObj.field === 'createdAt' && sortingObj.order === 'ASC') {
      return 'Sort: Oldest'
    }
    if (sortingObj.field === 'createdAt' && sortingObj.order === 'DESC') {
      return 'Sort: Newest'
    }
    if (sortingObj.field === 'star' && sortingObj.order === 'ASC') {
      return 'Sort: Lowest Rating'
    }
    if (sortingObj.field === 'star' && sortingObj.order === 'DESC') {
      return 'Sort: Highest Rating'
    }
  }

  const isFormValid = ratingValue > 0 && contentValue.trim().length > 0

  return (
    <>
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h3 className="text-xl font-semibold">Customer Reviews ({totalFeedbacks})</h3>
          {totalFeedbacks > 0 && (
            <div className="flex items-center gap-2 mt-1">
              <StarRating rating={averageStar} readOnly={true} />
              <span className="text-muted-foreground text-sm">
                {averageStar.toFixed(1)} out of 5
              </span>
            </div>
          )}
        </div>
        <div className="flex items-center gap-2">
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button variant="outline" className="w-[200px] justify-between">
                <span>{getSortLabel()}</span>
                <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent align="end" className="w-[200px]">
              <DropdownMenuItem
                onSelect={() =>
                  setSortingObj({
                    field: 'createdAt',
                    order: 'DESC',
                  })
                }
              >
                Newest
              </DropdownMenuItem>
              <DropdownMenuItem
                onSelect={() =>
                  setSortingObj({
                    field: 'createdAt',
                    order: 'ASC',
                  })
                }
              >
                Oldest
              </DropdownMenuItem>
              <DropdownMenuItem
                onSelect={() =>
                  setSortingObj({
                    field: 'star',
                    order: 'DESC',
                  })
                }
              >
                Highest Rating
              </DropdownMenuItem>
              <DropdownMenuItem
                onSelect={() =>
                  setSortingObj({
                    field: 'star',
                    order: 'ASC',
                  })
                }
              >
                Lowest Rating
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
          {sessionHelper.isLoggedInClient() ? (
            <Button onClick={() => setShowForm(!showForm)} variant="outline">
              {showForm ? <X className="mr-2 h-4 w-4" /> : <PenSquare className="mr-2 h-4 w-4" />}
              {showForm ? 'Cancel' : 'Write a Review'}
            </Button>
          ) : null}
        </div>
      </div>

      <Separator className="my-6" />

      {showForm && (
        <form
          onSubmit={handleSubmit(onFeedbackSubmit)}
          className="p-4 mb-6 border rounded-lg bg-secondary/30"
        >
          <h3 className="text-lg font-semibold mb-3">Your Review</h3>
          <div className="mb-4">
            <p className="text-sm font-medium mb-2">Overall rating</p>
            <Controller
              name="rating"
              control={control}
              render={({ field }) => (
                <StarRating rating={field.value} onRatingChange={field.onChange} size={24} />
              )}
            />
          </div>
          <Textarea
            {...register('content')}
            placeholder="Share your thoughts about the product..."
            className="mb-4 bg-background"
            disabled={isSubmitting}
          />
          <Button type="submit" disabled={isSubmitting || !isFormValid}>
            {isSubmitting ? 'Submitting ...' : 'Submit Review'}
          </Button>
        </form>
      )}
      {isSuccess ? (
        <>
          <ScrollArea className="h-[800px] pr-4">
            <div className="space-y-4">
              {feedbackList.length > 0 ? (
                feedbackList.map((feedback, index) => (
                  <div key={feedback.id}>
                    <FeedbackItem feedback={feedback} />
                    {index < feedbackList.length - 1 && <Separator />}
                  </div>
                ))
              ) : (
                <div className="text-center py-10">
                  <MessageSquare className="mx-auto h-12 w-12 text-muted-foreground mb-4" />
                  <p className="text-muted-foreground">
                    No reviews yet. Be the first to write one!
                  </p>
                </div>
              )}
            </div>
          </ScrollArea>
          <PaginationControlsClient
            currentPage={paginationObj.currentPage}
            totalPages={paginationObj.totalPages}
            showControls
            onPageChange={onPageChange}
          />
        </>
      ) : (
        <Spinner />
      )}
      {/* <AlertDialog open={!!feedbackToDelete} onOpenChange={(open) => !open && setFeedbackToDelete(null)}>
        <AlertDialogContent>
          <AlertDialogHeader>
            <AlertDialogTitle>Are you sure you want to delete this review?</AlertDialogTitle>
            <AlertDialogDescription>
              This action cannot be undone. This will permanently remove your feedback from the server.
            </AlertDialogDescription>
          </AlertDialogHeader>
          <AlertDialogFooter>
            <AlertDialogCancel onClick={() => setFeedbackToDelete(null)}>Cancel</AlertDialogCancel>
            <AlertDialogAction
              onClick={handleDeleteConfirm}
              className="bg-destructive hover:bg-destructive/90 text-destructive-foreground"
            >
              Delete
            </AlertDialogAction>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog> */}
    </>
  )
}

type FeedbackItemProps = {
  feedback: FeedbackExtended
  onEdit: (feedbackId: string) => void
  onDelete: (feedbackId: string) => void
}

const FeedbackItem = ({ feedback, onDelete, onEdit }: FeedbackItemProps) => {
  const { customer, star, content, createdAt, id } = feedback
  const customerHelper = new CustomerHelper(customer)
  const { getProfile } = useProfile()
  const session = useSession()
  const sessionHelper = new SessionHelper(session)
  const isOwner = sessionHelper.isLoggedInClient() && getProfile()?.userId === customerHelper.orElseNull()?.userId

  return (
    <div className="flex gap-4 py-4">
      <Avatar>
        <AvatarImage src={customerHelper.avatarUrl} alt={customerHelper.username} />
        <AvatarFallback>{extractInitials(customerHelper.username)}</AvatarFallback>
      </Avatar>
      <div className="flex-1">
        <div className="flex items-center justify-between">
          <h4 className="font-semibold">
            {customerHelper.firstName} {customerHelper.lastName}
          </h4>
          <div className="flex items-center gap-2">
            <span className="text-xs text-muted-foreground">
              {format(createdAt, DATETIME_FORMATS.DATE_TEXT_SHORT)}
            </span>
            {isOwner ? (
              <div className="flex gap-1">
                <Button variant="ghost" size="icon" className="h-7 w-7" onClick={() => onEdit(id)}>
                  <Pencil className="h-4 w-4" />
                </Button>
                <Button variant="ghost" size="icon" className="h-7 w-7 text-destructive hover:text-destructive" onClick={() => onDelete(id)}>
                  <Trash2 className="h-4 w-4" />
                </Button>
              </div>
            ) : null}
          </div>
        </div>
        <div className="my-2">
          <StarRating rating={star} readOnly={true} size={16} />
        </div>
        <p className="text-sm text-foreground/80 leading-relaxed">{content}</p>
      </div>
    </div>
  )
}
