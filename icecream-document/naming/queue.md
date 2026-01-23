# Queue naming convention

## Pattern

```
<domain>.<entity>.<action>.<type>.queue
```

- **Domain**: `infra`, `service`

  `service` If queue's purpose relates to application level

  `infra` if queue's purpose is to check for connectivity

- **Entity**: Transactional db entity (optional)
- **Action**: Should be verb
- **Type**: `task`, `ping`, `audit`
