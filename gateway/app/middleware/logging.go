package middleware

import (
	"log"

	"github.com/gin-gonic/gin"
)

func Logger(c *gin.Context) {
    log.Print("Hello World")
    c.Next()
}
