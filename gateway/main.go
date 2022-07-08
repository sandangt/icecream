package main

import (
	"icecream/gateway/app/route"

	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()
	router.GET("/albums", route.Handler)
	router.Run(":8080")
}
