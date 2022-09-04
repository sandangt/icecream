package model

import (
	"encoding/base64"
	"fmt"
	"net/http"
)

func basicAuthEncode(username, password string) string {
    auth := fmt.Sprintf("%s:%s", username, password)
    return base64.StdEncoding.EncodeToString([]byte(auth))
}

func GetAllProducts() (res *http.Response, err error) {
    client := &http.Client{}
    req, _ := http.NewRequest("GET", "http://localhost:31001/backoffice/", nil)
    req.Header.Add("Authorization", fmt.Sprintf("Basic %s", basicAuthEncode("sandang", "sandang")))
    res, err = client.Do(req)
    
    return
}
