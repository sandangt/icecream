'use client'

import { Search } from 'lucide-react'
import { ChangeEvent, FC } from 'react'

import { Input } from '@/components/ui/input'
import { Label } from '@radix-ui/react-label'
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@radix-ui/react-select'

type Props = {
  path: string
  searchParams?: {}
}

export const FilterGroup: FC<Props> = () => {
  return (
    <div className="flex flex-col md:flex-row justify-between items-center gap-6 p-4 bg-card rounded-lg shadow">
      <SearchBar onSearch={handleSearch} initialQuery={searchQuery} />
      <FilterControls
        categories={categories}
        selectedCategory={selectedCategory}
        onCategoryChange={handleCategoryChange}
      />
    </div>
  )
}

type FilterControlsProps = {
  categories: string[]
  selectedCategory: string
  onCategoryChange: (category: string) => void
}

const FilterControls = ({
  categories,
  selectedCategory,
  onCategoryChange,
}: FilterControlsProps) => {
  return (
    <div className="flex flex-col sm:flex-row gap-4 items-start sm:items-center">
      <div>
        <Label htmlFor="category-filter" className="text-sm font-medium mb-1 block sr-only">
          Filter by Category
        </Label>
        <Select value={selectedCategory} onValueChange={onCategoryChange}>
          <SelectTrigger
            id="category-filter"
            className="w-full sm:w-[200px] rounded-md shadow-sm"
            aria-label="Filter by category"
          >
            <SelectValue placeholder="All Categories" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="all">All Categories</SelectItem>
            {categories.map((category) => (
              <SelectItem key={category} value={category}>
                {category}
              </SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>
    </div>
  )
}

type SearchBarProps = {
  onSearch: (query: string) => void
  placeholder?: string
  initialQuery?: string
}

const SearchBar = ({
  onSearch,
  placeholder = 'Search products...',
  initialQuery = '',
}: SearchBarProps) => {
  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    onSearch(event.target.value)
  }

  return (
    <div className="relative w-full md:w-1/2 lg:w-1/3">
      <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 h-5 w-5 text-muted-foreground" />
      <Input
        type="search"
        defaultValue={initialQuery}
        placeholder={placeholder}
        onChange={handleInputChange}
        className="pl-10 pr-4 py-2 w-full rounded-md border shadow-sm focus:ring-primary focus:border-primary"
        aria-label="Search products"
      />
    </div>
  )
}
