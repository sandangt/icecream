const Page = () => (
  <div className="min-h-screen flex items-center justify-center bg-gray-100 py-12 px-4 sm:px-6 lg:px-8">
    <div className="max-w-lg w-full space-y-8 bg-white p-8 rounded-lg shadow-md">
      <div className="text-center">
        <h1 className="text-4xl font-extrabold text-gray-900">Contact Us</h1>
        <p className="mt-4 text-lg leading-6 text-gray-600">
          Have any questions or concerns? We're always here to help.
        </p>
      </div>
      <form className="mt-8 space-y-6">
        <div>
          <label htmlFor="name" className="block text-sm font-medium text-gray-700">
            Name
          </label>
          <input
            id="name"
            name="name"
            type="text"
            required
            className="mt-1 p-2 w-full border border-gray-300 rounded-md shadow-sm focus:ring-zinc-700 focus:border-zinc-700"
          />
        </div>
        <div>
          <label htmlFor="email" className="block text-sm font-medium text-gray-700">
            Email
          </label>
          <input
            id="email"
            name="email"
            type="email"
            required
            className="mt-1 p-2 w-full border border-gray-300 rounded-md shadow-sm focus:ring-zinc-700 focus:border-zinc-700"
          />
        </div>
        <div>
          <label htmlFor="message" className="block text-sm font-medium text-gray-700">
            Message
          </label>
          <textarea
            id="message"
            name="message"
            rows={4}
            required
            className="mt-1 p-2 w-full border border-gray-300 rounded-md shadow-sm focus:ring-zinc-700 focus:border-zinc-700"
          ></textarea>
        </div>
        <div>
          <button
            type="submit"
            className="w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-slate-800 hover:bg-slate-800 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-zinc-700"
          >
            Send Message
          </button>
        </div>
      </form>
    </div>
  </div>
)

export default Page
