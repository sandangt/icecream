'use server'

import { SITE_NAME } from '@/lib/constants'

export const Footer = async () => (
  <footer className="bg-secondary text-secondary-foreground py-8 border-t">
    <div className="container mx-auto px-4 text-center">
      <p className="text-sm">
        &copy; {new Date().getFullYear()} {SITE_NAME}. All rights reserved.
      </p>
      <p className="text-xs mt-1">Powered by Modern Technology.</p>
    </div>
  </footer>
)
