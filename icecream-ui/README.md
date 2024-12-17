# Icecream Shop UI

## Installation

```bash
> pnpm install
```

## Running dev mode

```bash
> pnpm install
```

## Debug

- To debug server-side code, paste below script to .vscode/launch.json file

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "debug server-side",
      "type": "node",
      "request": "launch",
      "runtimeExecutable": "${workspaceFolder}/node_modules/.bin/next",
      "runtimeArgs": ["dev", "-p", "34000"],
      "envFile": "${workspaceFolder}/.env"
    }
  ]
}
```

