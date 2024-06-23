'use client'

import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu'
import { useSessionStore } from '@/hooks/session'
import { signIn, signOut } from 'next-auth/react'

export const Profile = () => {
  const isLoggedIn = useSessionStore((state) => state.isLoggedIn)
  const accessToken = useSessionStore((state) => state.accessToken)
  const refreshToken = useSessionStore((state) => state.refreshToken)
  const clearSession = useSessionStore((state) => state.clearSession)
  const loginClick = () => signIn('keycloak')
  const logoutClick = () => {
    clearSession()
    signOut()
  }
  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Avatar>
          {isLoggedIn ? <AvatarImage src="https://github.com/shadcn.png" alt="@shadcn" /> : null}
          <AvatarFallback>CN</AvatarFallback>
        </Avatar>
      </DropdownMenuTrigger>
      <DropdownMenuContent>
        {isLoggedIn ? (
          <DropdownMenuItem onClick={logoutClick}>Logout</DropdownMenuItem>
        ) : (
          <DropdownMenuItem onClick={loginClick}>Login</DropdownMenuItem>
        )}
      </DropdownMenuContent>
    </DropdownMenu>
  )
}
