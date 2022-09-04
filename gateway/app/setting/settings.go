package setting

import (
	helper "icecream/gateway/app/util"
)

var (
	PORT string = helper.GetEnvDefault("PORT", "31000")
	HOST string = helper.GetEnvDefault("HOST", "localhost")
)
