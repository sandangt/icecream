export type ClientEnvConf = {
  CONSUL_URL: string
  ECHO_URL: string
  STORAGE_URL: string
}

type EnvConf = ClientEnvConf & {
  AUTH_KEYCLOAK_ID: string
  AUTH_KEYCLOAK_SECRET: string
  AUTH_KEYCLOAK_ISSUER: string
  STORAGE_ACCESS_KEY: string
  STORAGE_SECRET_KEY: string
  STORAGE_IMAGE_BUCKET: string
}

const getEnv = <K extends keyof EnvConf>(key: K): string => {
  return typeof window === 'undefined' ?
    process.env[key] as string :
    (window as any).__ENV__[key]
}

export const CONSUL_URL = getEnv('CONSUL_URL')
export const ECHO_URL = getEnv('ECHO_URL')
export const STORAGE_URL = getEnv('STORAGE_URL')
export const AUTH_KEYCLOAK_ID = getEnv('AUTH_KEYCLOAK_ID')
export const AUTH_KEYCLOAK_SECRET = getEnv('AUTH_KEYCLOAK_SECRET')
export const AUTH_KEYCLOAK_ISSUER = getEnv('AUTH_KEYCLOAK_ISSUER')
export const STORAGE_ACCESS_KEY = getEnv('STORAGE_ACCESS_KEY')
export const STORAGE_SECRET_KEY = getEnv('STORAGE_SECRET_KEY')
export const STORAGE_IMAGE_BUCKET = getEnv('STORAGE_IMAGE_BUCKET')
