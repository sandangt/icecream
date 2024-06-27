export const ProductList = () => (
  <div className="col-span-3">
    <div className="flex items-center mb-4">
      <select
        name="sort"
        id="sort"
        className="w-44 text-sm text-gray-600 py-3 px-4 border-gray-300 shadow-sm rounded focus:ring-primary focus:border-primary"
      >
        <option value="">Default sorting</option>
        <option value="price-low-to-high">Price low to high</option>
        <option value="price-high-to-low">Price high to low</option>
        <option value="latest">Latest product</option>
      </select>

      <div className="flex gap-2 ml-auto">
        <div className="border border-primary w-10 h-9 flex items-center justify-center text-white bg-primary rounded cursor-pointer">
          <i className="fa-solid fa-grip-vertical"></i>
        </div>
        <div className="border border-gray-300 w-10 h-9 flex items-center justify-center text-gray-600 rounded cursor-pointer">
          <i className="fa-solid fa-list"></i>
        </div>
      </div>
    </div>

    <div className="grid md:grid-cols-3 grid-cols-2 gap-6">
      <div className="bg-white shadow rounded overflow-hidden group">
        <div className="relative">
          <img src="/img/product1.jpg" alt="product 1" className="w-full" />
          <div
            className="absolute inset-0 bg-black bg-opacity-40 flex items-center
                        justify-center gap-2 opacity-0 group-hover:opacity-100 transition"
          >
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="view product"
            >
              <i className="fa-solid fa-magnifying-glass"></i>
            </a>
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="add to wishlist"
            >
              <i className="fa-solid fa-heart"></i>
            </a>
          </div>
        </div>
        <div className="pt-4 pb-3 px-4">
          <a href="#">
            <h4 className="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">
              Guyer Chair
            </h4>
          </a>
          <div className="flex items-baseline mb-1 space-x-2">
            <p className="text-xl text-primary font-semibold">$45.00</p>
            <p className="text-sm text-gray-400 line-through">$55.90</p>
          </div>
          <div className="flex items-center">
            <div className="flex gap-1 text-sm text-yellow-400">
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
            </div>
            <div className="text-xs text-gray-500 ml-3">(150)</div>
          </div>
        </div>
        <a
          href="#"
          className="block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition"
        >
          Add to cart
        </a>
      </div>

      <div className="bg-white shadow rounded overflow-hidden group">
        <div className="relative">
          <img src="/img/product2.jpg" alt="product 1" className="w-full" />
          <div
            className="absolute inset-0 bg-black bg-opacity-40 flex items-center
                        justify-center gap-2 opacity-0 group-hover:opacity-100 transition"
          >
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="view product"
            >
              <i className="fa-solid fa-magnifying-glass"></i>
            </a>
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="add to wishlist"
            >
              <i className="fa-solid fa-heart"></i>
            </a>
          </div>
        </div>
        <div className="pt-4 pb-3 px-4">
          <a href="#">
            <h4 className="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">
              Guyer Chair
            </h4>
          </a>
          <div className="flex items-baseline mb-1 space-x-2">
            <p className="text-xl text-primary font-semibold">$45.00</p>
            <p className="text-sm text-gray-400 line-through">$55.90</p>
          </div>
          <div className="flex items-center">
            <div className="flex gap-1 text-sm text-yellow-400">
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
            </div>
            <div className="text-xs text-gray-500 ml-3">(150)</div>
          </div>
        </div>
        <a
          href="#"
          className="block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition"
        >
          Add to cart
        </a>
      </div>

      <div className="bg-white shadow rounded overflow-hidden group">
        <div className="relative">
          <img src="/img/product3.jpg" alt="product 1" className="w-full" />
          <div
            className="absolute inset-0 bg-black bg-opacity-40 flex items-center
                        justify-center gap-2 opacity-0 group-hover:opacity-100 transition"
          >
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="view product"
            >
              <i className="fa-solid fa-magnifying-glass"></i>
            </a>
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="add to wishlist"
            >
              <i className="fa-solid fa-heart"></i>
            </a>
          </div>
        </div>
        <div className="pt-4 pb-3 px-4">
          <a href="#">
            <h4 className="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">
              Guyer Chair
            </h4>
          </a>
          <div className="flex items-baseline mb-1 space-x-2">
            <p className="text-xl text-primary font-semibold">$45.00</p>
            <p className="text-sm text-gray-400 line-through">$55.90</p>
          </div>
          <div className="flex items-center">
            <div className="flex gap-1 text-sm text-yellow-400">
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
            </div>
            <div className="text-xs text-gray-500 ml-3">(150)</div>
          </div>
        </div>
        <a
          href="#"
          className="block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition"
        >
          Add to cart
        </a>
      </div>

      <div className="bg-white shadow rounded overflow-hidden group">
        <div className="relative">
          <img src="/img/product4.jpg" alt="product 1" className="w-full" />
          <div
            className="absolute inset-0 bg-black bg-opacity-40 flex items-center
                        justify-center gap-2 opacity-0 group-hover:opacity-100 transition"
          >
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="view product"
            >
              <i className="fa-solid fa-magnifying-glass"></i>
            </a>
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="add to wishlist"
            >
              <i className="fa-solid fa-heart"></i>
            </a>
          </div>
        </div>
        <div className="pt-4 pb-3 px-4">
          <a href="#">
            <h4 className="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">
              Guyer Chair
            </h4>
          </a>
          <div className="flex items-baseline mb-1 space-x-2">
            <p className="text-xl text-primary font-semibold">$45.00</p>
            <p className="text-sm text-gray-400 line-through">$55.90</p>
          </div>
          <div className="flex items-center">
            <div className="flex gap-1 text-sm text-yellow-400">
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
            </div>
            <div className="text-xs text-gray-500 ml-3">(150)</div>
          </div>
        </div>
        <a
          href="#"
          className="block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition"
        >
          Add to cart
        </a>
      </div>

      <div className="bg-white shadow rounded overflow-hidden group">
        <div className="relative">
          <img src="/img/product5.jpg" alt="product 1" className="w-full" />
          <div
            className="absolute inset-0 bg-black bg-opacity-40 flex items-center
                        justify-center gap-2 opacity-0 group-hover:opacity-100 transition"
          >
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="view product"
            >
              <i className="fa-solid fa-magnifying-glass"></i>
            </a>
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="add to wishlist"
            >
              <i className="fa-solid fa-heart"></i>
            </a>
          </div>
        </div>
        <div className="pt-4 pb-3 px-4">
          <a href="#">
            <h4 className="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">
              Guyer Chair
            </h4>
          </a>
          <div className="flex items-baseline mb-1 space-x-2">
            <p className="text-xl text-primary font-semibold">$45.00</p>
            <p className="text-sm text-gray-400 line-through">$55.90</p>
          </div>
          <div className="flex items-center">
            <div className="flex gap-1 text-sm text-yellow-400">
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
            </div>
            <div className="text-xs text-gray-500 ml-3">(150)</div>
          </div>
        </div>
        <a
          href="#"
          className="block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition"
        >
          Add to cart
        </a>
      </div>

      <div className="bg-white shadow rounded overflow-hidden group">
        <div className="relative">
          <img src="/img/product6.jpg" alt="product 1" className="w-full" />
          <div
            className="absolute inset-0 bg-black bg-opacity-40 flex items-center
                        justify-center gap-2 opacity-0 group-hover:opacity-100 transition"
          >
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="view product"
            >
              <i className="fa-solid fa-magnifying-glass"></i>
            </a>
            <a
              href="#"
              className="text-white text-lg w-9 h-8 rounded-full bg-primary flex items-center justify-center hover:bg-gray-800 transition"
              title="add to wishlist"
            >
              <i className="fa-solid fa-heart"></i>
            </a>
          </div>
        </div>
        <div className="pt-4 pb-3 px-4">
          <a href="#">
            <h4 className="uppercase font-medium text-xl mb-2 text-gray-800 hover:text-primary transition">
              Guyer Chair
            </h4>
          </a>
          <div className="flex items-baseline mb-1 space-x-2">
            <p className="text-xl text-primary font-semibold">$45.00</p>
            <p className="text-sm text-gray-400 line-through">$55.90</p>
          </div>
          <div className="flex items-center">
            <div className="flex gap-1 text-sm text-yellow-400">
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
              <span>
                <i className="fa-solid fa-star"></i>
              </span>
            </div>
            <div className="text-xs text-gray-500 ml-3">(150)</div>
          </div>
        </div>
        <a
          href="#"
          className="block w-full py-1 text-center text-white bg-primary border border-primary rounded-b hover:bg-transparent hover:text-primary transition"
        >
          Add to cart
        </a>
      </div>
    </div>
  </div>
)
