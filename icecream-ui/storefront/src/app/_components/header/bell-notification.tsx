'use client'

import { format } from 'date-fns'
import { Bell } from 'lucide-react'

import { Badge } from '@/components/ui/badge'
import { Button } from '@/components/ui/button'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuLabel,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { useNotification } from '@/hooks'
import { DATETIME_FORMATS } from '@/lib/constants'
import { cn, truncateTxt } from '@/lib/utils'

export const BellNotification = () => {
  const { isUnnoticed, getNewMessageNum, getNewMessages, setUnnoticed } = useNotification()

  const handleNotificationMenuOpen = (open: boolean) => {
    setUnnoticed(false)
  }

  return (
    <DropdownMenu onOpenChange={handleNotificationMenuOpen}>
      <DropdownMenuTrigger asChild>
        <Button variant="ghost" size="icon" aria-label="Notifications" className="relative">
          <Bell className="h-5 w-5 sm:h-6 sm:w-6 text-foreground" />
          {isUnnoticed() && getNewMessageNum() > 0 ? (
            <Badge
              variant="destructive"
              className="absolute -top-1 -right-1 px-1.5 h-5 min-w-[20px] flex items-center justify-center text-xs"
            >
              {getNewMessageNum()}
            </Badge>
          ) : null}
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end" className="w-96">
        <DropdownMenuLabel>Notifications</DropdownMenuLabel>
        <DropdownMenuSeparator />
        {getNewMessages()?.length > 0 ? (
          <div className="max-h-[400px] overflow-y-auto p-1">
            {getNewMessages().map((message) => (
              <DropdownMenuItem
                key={message.id}
                asChild
                className={cn(
                  'h-auto cursor-pointer p-0 focus:bg-accent',
                  !message.seen && 'bg-secondary/50',
                )}
              >
                {/* <Link href={notification.link || '#'} className="block p-3"> */}
                <div className="flex items-start gap-3">
                  <div
                    className="w-2 h-2 rounded-full bg-primary mt-1.5 shrink-0 transition-opacity duration-500"
                    style={{ opacity: message.seen ? 0 : 1 }}
                  />
                  <div className="flex-grow">
                    <p className="text-sm text-foreground whitespace-normal font-bold">
                      {message.title}
                    </p>
                    <p className="text-sm text-foreground whitespace-normal">
                      {truncateTxt(message.content, 50)}
                    </p>
                    <p className="text-xs text-muted-foreground mt-1">
                      {format(message.createdAt, DATETIME_FORMATS.DATE_TEXT_SHORT)}
                    </p>
                  </div>
                </div>
                {/* </Link> */}
              </DropdownMenuItem>
            ))}
          </div>
        ) : (
          <div className="p-4 text-center text-muted-foreground">
            You have no new notifications.
          </div>
        )}
      </DropdownMenuContent>
    </DropdownMenu>
  )
}
