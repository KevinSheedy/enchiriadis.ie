package ie.enchiriadis

class Mp3Controller {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [mp3InstanceList: Mp3.list(params), mp3InstanceTotal: Mp3.count()]
    }

    def jsonList = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

		render(view: "jsonList", model: [mp3InstanceList: Mp3.list(params), mp3InstanceTotal: Mp3.count()])
    }

    def create = {
        def mp3Instance = new Mp3()
        mp3Instance.properties = params
        return [mp3Instance: mp3Instance]
    }

    def save = {
        def mp3Instance = new Mp3(params)
		mp3Instance.name = params.audio.fileItem.fileName
        if (mp3Instance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'mp3.label', default: 'Mp3'), mp3Instance.id])}"
            redirect(action: "show", id: mp3Instance.id)
        }
        else {
            render(view: "create", model: [mp3Instance: mp3Instance])
        }
    }

    def show = {
        def mp3Instance = Mp3.get(params.id)
        if (!mp3Instance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'mp3.label', default: 'Mp3'), params.id])}"
            redirect(action: "list")
        }
        else {
            [mp3Instance: mp3Instance]
        }
    }

    def edit = {
        def mp3Instance = Mp3.get(params.id)
        if (!mp3Instance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'mp3.label', default: 'Mp3'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [mp3Instance: mp3Instance]
        }
    }

    def update = {
        def mp3Instance = Mp3.get(params.id)
        if (mp3Instance) {
            if (params.version) {
                def version = params.version.toLong()
                if (mp3Instance.version > version) {
                    
                    mp3Instance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'mp3.label', default: 'Mp3')] as Object[], "Another user has updated this Mp3 while you were editing")
                    render(view: "edit", model: [mp3Instance: mp3Instance])
                    return
                }
            }
            mp3Instance.properties = params
            if (!mp3Instance.hasErrors() && mp3Instance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'mp3.label', default: 'Mp3'), mp3Instance.id])}"
                redirect(action: "show", id: mp3Instance.id)
            }
            else {
                render(view: "edit", model: [mp3Instance: mp3Instance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'mp3.label', default: 'Mp3'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def mp3Instance = Mp3.get(params.id)
        if (mp3Instance) {
            try {
                mp3Instance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'mp3.label', default: 'Mp3'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'mp3.label', default: 'Mp3'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'mp3.label', default: 'Mp3'), params.id])}"
            redirect(action: "list")
        }
    }

	def getAudio = {
		def mp3Instance = Mp3.get(params.id)
		response.setContentType("audio/mpeg")
		byte[] audio = mp3Instance.audio
		response.outputStream << audio
	}
}
