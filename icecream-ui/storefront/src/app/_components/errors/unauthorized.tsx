'use client'

import { signIn } from 'next-auth/react'
import Link from 'next/link'

import { Button } from '@/components/ui/button'
import { ROUTES } from '@/lib/constants'

export const UnauthorizationError = () => (
  <div className="h-[75vh] flex flex-col justify-center items-center">
    <h1 className="text-8xl font-extrabold text-blue-600">401</h1>
    <p className="text-4xl font-medium">Unauthorized Page</p>
    <p className="text-xl mt-4">Please log in to access this page.</p>
    <div className="mt-4 flex items-center justify-center gap-4">
      <Button variant="default" onClick={() => signIn('keycloak')}>
        Login
      </Button>
      <Button variant="default" asChild>
        <Link href={ROUTES.HOME}>Home</Link>
      </Button>
    </div>
  </div>
)
