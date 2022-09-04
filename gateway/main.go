package main

import (
	"fmt"
	"net/http"

	"icecream/gateway/app/route"
	"icecream/gateway/app/setting"
)

func runServer() {
	server := &http.Server{
		Addr:    fmt.Sprintf("%s:%s", setting.HOST, setting.PORT),
		Handler: route.Router(),
	}
	server.ListenAndServe()
}

func main() {
	runServer()
}
