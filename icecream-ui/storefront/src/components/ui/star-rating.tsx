'use client'

import { Star } from 'lucide-react'
import { useState } from 'react'

import { cn } from '@/lib/utils'

interface StarRatingProps {
  rating: number
  totalStars?: number
  size?: number
  fillColor?: string
  emptyColor?: string
  onRatingChange?: (rating: number) => void
  readOnly?: boolean
}

export const StarRating = ({
  rating,
  totalStars = 5,
  size = 20,
  fillColor = 'text-primary',
  emptyColor = 'text-muted-foreground/30',
  onRatingChange,
  readOnly = false,
}: StarRatingProps) => {
  const [hoverRating, setHoverRating] = useState<number | null>(null)

  const handleMouseEnter = (index: number) => {
    if (!readOnly) {
      setHoverRating(index + 1)
    }
  }

  const handleMouseLeave = () => {
    if (!readOnly) {
      setHoverRating(null)
    }
  }

  const handleClick = (index: number) => {
    if (!readOnly && onRatingChange) {
      onRatingChange(index + 1)
    }
  }

  return (
    <div className={cn('flex items-center', !readOnly && 'cursor-pointer')}>
      {[...Array(totalStars)].map((_, index) => {
        const starValue = index + 1
        const isFilled = starValue <= (hoverRating ?? rating)

        return (
          <Star
            key={index}
            size={size}
            className={cn('transition-colors', isFilled ? fillColor : emptyColor)}
            onMouseEnter={() => handleMouseEnter(index)}
            onMouseLeave={handleMouseLeave}
            onClick={() => handleClick(index)}
            fill={isFilled ? 'currentColor' : 'none'}
          />
        )
      })}
    </div>
  )
}
