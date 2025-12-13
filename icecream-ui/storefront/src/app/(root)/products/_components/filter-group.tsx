'use client'

import { Filter, X, ChevronUp, SlidersHorizontal, Search } from 'lucide-react'
import { FC, useCallback, useState } from 'react'
import { useRouter, useSearchParams } from 'next/navigation'

import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Button } from '@/components/ui/button'
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select'
import { Category } from '@/models/core'

type Props = {
  path: string
  categories: Category[]
}

const PRODUCT_STATUSES = ['ACTIVE', 'INACTIVE', 'DRAFT', 'OUT_OF_STOCK']

export const FilterGroup: FC<Props> = ({ path, categories }) => {
  const router = useRouter()
  const searchParams = useSearchParams()

  const [search, setSearch] = useState(searchParams.get('search') || '')
  const [category, setCategory] = useState(searchParams.get('category') || 'all')
  const [status, setStatus] = useState(searchParams.get('status') || 'all')
  const [minPrice, setMinPrice] = useState(searchParams.get('minPrice') || '')
  const [maxPrice, setMaxPrice] = useState(searchParams.get('maxPrice') || '')
  const [showAdvanced, setShowAdvanced] = useState(false)
  const [isVisible, setIsVisible] = useState(false)

  const updateURL = useCallback(
    (filters: Record<string, string>) => {
      const params = new URLSearchParams(searchParams.toString())

      // Remove page when filters change
      params.delete('page')

      Object.entries(filters).forEach(([key, value]) => {
        if (value && value !== 'all' && value !== '') {
          params.set(key, value)
        } else {
          params.delete(key)
        }
      })

      router.push(`${path}?${params.toString()}`)
    },
    [path, router, searchParams]
  )

  const handleSearch = useCallback(
    (query: string) => {
      setSearch(query)
      const timeout = setTimeout(() => {
        updateURL({ search: query })
      }, 500)
      return () => clearTimeout(timeout)
    },
    [updateURL]
  )

  const handleCategoryChange = (value: string) => {
    setCategory(value)
    updateURL({ category: value, search, status, minPrice, maxPrice })
  }

  const handleStatusChange = (value: string) => {
    setStatus(value)
    updateURL({ category, search, status: value, minPrice, maxPrice })
  }

  const handlePriceFilter = () => {
    updateURL({ category, search, status, minPrice, maxPrice })
  }

  const clearFilters = () => {
    setSearch('')
    setCategory('all')
    setStatus('all')
    setMinPrice('')
    setMaxPrice('')
    router.push(path)
  }

  const hasActiveFilters = search || category !== 'all' || status !== 'all' || minPrice || maxPrice
  const activeFilterCount = Object.values({ search, category, status, minPrice, maxPrice }).filter(
    (v) => v && v !== 'all'
  ).length

  return (
    <div className="relative">
      {/* Floating Action Button - Always Visible */}
      {!isVisible && (
        <div className="flex justify-end">
          <Button
            onClick={() => setIsVisible(true)}
            className="shadow-lg relative gap-2"
            size="default"
            title="Search & Filter Products"
          >
            <SlidersHorizontal className="h-4 w-4" />
            Search Products
            {hasActiveFilters && (
              <span className="ml-1 px-2 py-0.5 text-xs bg-background text-foreground rounded-full">
                {activeFilterCount}
              </span>
            )}
          </Button>
        </div>
      )}

      {/* Filter Card - Hidden by Default */}
      {isVisible && (
        <div className="bg-card rounded-lg shadow-md border">
          {/* Search and Actions Row */}
          <div className="p-4 flex flex-col sm:flex-row gap-3 items-stretch sm:items-center">
            <div className="relative flex-1">
              <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 h-4 w-4 text-muted-foreground" />
              <Input
                type="search"
                defaultValue={search}
                placeholder="Search products..."
                onChange={(e) => handleSearch(e.target.value)}
                className="pl-9 h-10"
                aria-label="Search products"
              />
            </div>

            <div className="flex gap-2 sm:flex-shrink-0">
              <Button
                variant={showAdvanced ? 'default' : 'outline'}
                size="default"
                onClick={() => setShowAdvanced(!showAdvanced)}
                className="flex-1 sm:flex-initial gap-2"
              >
                <Filter className="h-4 w-4" />
                Filters
              </Button>

              {hasActiveFilters && (
                <Button variant="ghost" size="default" onClick={clearFilters} className="gap-2">
                  <X className="h-4 w-4" />
                  Clear
                </Button>
              )}

              <Button
                variant="ghost"
                size="icon"
                onClick={() => setIsVisible(false)}
                className="sm:flex-shrink-0"
                title="Hide search and filters"
              >
                <ChevronUp className="h-4 w-4" />
              </Button>
            </div>
          </div>

          {/* Advanced Filters Section */}
          {showAdvanced && (
            <div className="border-t px-4 py-4 space-y-4 bg-muted/30">
              <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
                <div className="space-y-2">
                  <Label htmlFor="category-filter" className="text-sm font-medium">
                    Category
                  </Label>
                  <Select value={category} onValueChange={handleCategoryChange}>
                    <SelectTrigger id="category-filter" className="w-full bg-background">
                      <SelectValue placeholder="All Categories" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="all">All Categories</SelectItem>
                      {categories.map((cat) => (
                        <SelectItem key={cat.id} value={cat.slug}>
                          {cat.name}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>

                <div className="space-y-2">
                  <Label htmlFor="status-filter" className="text-sm font-medium">
                    Status
                  </Label>
                  <Select value={status} onValueChange={handleStatusChange}>
                    <SelectTrigger id="status-filter" className="w-full bg-background">
                      <SelectValue placeholder="All Statuses" />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="all">All Statuses</SelectItem>
                      {PRODUCT_STATUSES.map((stat) => (
                        <SelectItem key={stat} value={stat}>
                          {stat.replace('_', ' ')}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>

                <div className="space-y-2">
                  <Label htmlFor="min-price" className="text-sm font-medium">
                    Min Price
                  </Label>
                  <Input
                    id="min-price"
                    type="number"
                    min="0"
                    step="0.01"
                    value={minPrice}
                    onChange={(e) => setMinPrice(e.target.value)}
                    placeholder="0.00"
                    className="w-full bg-background"
                  />
                </div>

                <div className="space-y-2">
                  <Label htmlFor="max-price" className="text-sm font-medium">
                    Max Price
                  </Label>
                  <Input
                    id="max-price"
                    type="number"
                    min="0"
                    step="0.01"
                    value={maxPrice}
                    onChange={(e) => setMaxPrice(e.target.value)}
                    placeholder="999.99"
                    className="w-full bg-background"
                  />
                </div>
              </div>

              {(minPrice || maxPrice) && (
                <div className="flex justify-end pt-2">
                  <Button onClick={handlePriceFilter} size="sm" className="gap-2">
                    Apply Price Filter
                  </Button>
                </div>
              )}
            </div>
          )}
        </div>
      )}
    </div>
  )
}
