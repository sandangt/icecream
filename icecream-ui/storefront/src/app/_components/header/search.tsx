import { useRouter, useSearchParams } from 'next/navigation'
import { BiSearchAlt } from 'react-icons/bi'
import { MdOutlineSearch } from 'react-icons/md'

export const Search = () => (
  <div className="w-full max-w-xl relative flex">
    <span className="absolute left-4 top-4">
      <BiSearchAlt size="1.25rem" />
    </span>
    <input
      type="text"
      name="search"
      id="search"
      className="w-full border border-primary border-r-0 pl-12 py-3 pr-3 rounded-l-md focus:outline-none"
      placeholder="search"
    />
    <button className="bg-primary text-primary-foreground border border-primary px-8 rounded-r-md hover:bg-transparent hover:text-primary transition">
      Search
    </button>
  </div>
)
