# -------- Build stage --------
FROM node:24.12-alpine AS builder

WORKDIR /app

COPY package.json pnpm-lock.yaml* ./

RUN npm install -g pnpm
RUN pnpm install

COPY . .

RUN pnpm build

# -------- Runtime --------
FROM node:24.12-alpine

WORKDIR /app

RUN npm install -g pnpm

COPY --from=builder /app ./

ENV NODE_ENV=production

EXPOSE 3000

CMD ["pnpm", "start"]
