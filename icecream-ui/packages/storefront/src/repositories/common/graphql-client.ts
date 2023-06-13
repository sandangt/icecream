import { GraphQLClient } from 'graphql-request'

const gatewayEndpoint = `${process.env.GATEWAY_PROTOCOL}://${process.env.GATEWAY_HOST}:${process.env.GATEWAY_PORT}/graphql`

const client = new GraphQLClient(gatewayEndpoint)

export default client
