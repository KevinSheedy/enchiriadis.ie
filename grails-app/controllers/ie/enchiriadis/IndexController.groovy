package ie.enchiriadis

class IndexController {

    def index = {
		[metaInstanceList: Meta.list(params), metaInstanceTotal: Fan.count()]
		//redirect(action: "index", params: params)
	}
}
