import Link from 'next/link'
import { FaFacebook } from 'react-icons/fa'
import { FaTwitter } from 'react-icons/fa'
import { FaInstagram } from 'react-icons/fa'

export const Footer = () => (
  <footer className="py-12 border-border flex flex-col px-4 sm:px-6 lg:px-8 gap-8">
    <hr className="text-secondary-foreground" />
    <div className="max-w-7xl mx-auto">
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <div>
          <h3 className="text-lg font-semibold mb-4">About Us</h3>
          <p className="text-sm">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla quam velit.
          </p>
        </div>
        <div>
          <h3 className="text-lg font-semibold mb-4">Contact Us</h3>
          <p className="text-sm">123 Street, City, Country</p>
          <p className="text-sm">email@example.com</p>
          <p className="text-sm">+123 456 7890</p>
        </div>
        <div>
          <h3 className="text-lg font-semibold mb-4">Follow Us</h3>
          <div className="flex space-x-4">
            <Link href="#" className="text-gray-800 hover:text-secondary-foreground transition duration-300">
              <FaFacebook />
            </Link>
            <Link href="#" className="text-gray-800 hover:text-secondary-foreground transition duration-300">
              <FaTwitter />
            </Link>
            <Link href="#" className="text-gray-800 hover:text-secondary-foreground transition duration-300">
              <FaInstagram />
            </Link>
          </div>
        </div>
      </div>
    </div>
    <hr className="text-secondary-foreground" />
    <div className="w-7xl mx-auto">
      <div className="flex justify-between items-center text-sm">
        <p>&copy; 2024 Your Company. All rights reserved.</p>
        <div className="flex space-x-4">
          <Link href="#" className="text-gray-800 hover:text-secondary-foreground transition duration-300">
            Privacy Policy
          </Link>
          <Link href="#" className="text-gray-800 hover:text-secondary-foreground transition duration-300">
            Terms of Service
          </Link>
        </div>
      </div>
    </div>
  </footer>
)
