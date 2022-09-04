package route

import (
	productService "icecream/gateway/app/controller/product"
	middleman "icecream/gateway/app/middleware"

	"github.com/gin-gonic/gin"
)

func Router() (root *gin.Engine) {
	root = gin.New()
    root.Use(middleman.Logger)
	root.GET("/products", productService.GetAllProducts)

	return   
}
