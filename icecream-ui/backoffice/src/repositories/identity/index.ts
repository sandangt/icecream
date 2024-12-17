import Keycloak, {
  type KeycloakConfig,
  type KeycloakTokenParsed,
} from 'keycloak-js'
import { keycloakAuthProvider } from 'ra-keycloak'

import { IDENTITY_CLIENT_ID, IDENTITY_REALM, IDENTITY_URL } from '@/settings'
import { DEFAULT_ROLE } from '@/constants'

const config: KeycloakConfig = {
  url: IDENTITY_URL,
  realm: IDENTITY_REALM,
  clientId: IDENTITY_CLIENT_ID,
}
const keycloakClient = new Keycloak(config)
const raKeycloakOptions = {
  onPermissions: (decoded: KeycloakTokenParsed) => {
    const roles = decoded?.realm_access?.roles
    if (!roles || !roles.length || !roles.includes(DEFAULT_ROLE)) {
      return false
    }
    return DEFAULT_ROLE
  },
}
keycloakClient.init({ onLoad: 'login-required' })

export default keycloakAuthProvider(keycloakClient, raKeycloakOptions)
