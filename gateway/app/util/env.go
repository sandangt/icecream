package util

import "os"

func GetEnvDefault(key, def string) (val string) {
	val = os.Getenv(key)
	if val == "" {
		return def
	}
	return
}
