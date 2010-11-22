package ie.enchiriadis

class TextController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [textInstanceList: Text.list(params), textInstanceTotal: Text.count()]
    }

    def create = {
        def textInstance = new Text()
        textInstance.properties = params
        return [textInstance: textInstance]
    }

    def save = {
        def textInstance = new Text(params)
        if (textInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'text.label', default: 'Text'), textInstance.id])}"
            redirect(action: "show", id: textInstance.id)
        }
        else {
            render(view: "create", model: [textInstance: textInstance])
        }
    }

    def show = {
        def textInstance = Text.get(params.id)
        if (!textInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'text.label', default: 'Text'), params.id])}"
            redirect(action: "list")
        }
        else {
            [textInstance: textInstance]
        }
    }

    def edit = {
        def textInstance = Text.get(params.id)
        if (!textInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'text.label', default: 'Text'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [textInstance: textInstance]
        }
    }

    def update = {
        def textInstance = Text.get(params.id)
        if (textInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (textInstance.version > version) {
                    
                    textInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'text.label', default: 'Text')] as Object[], "Another user has updated this Text while you were editing")
                    render(view: "edit", model: [textInstance: textInstance])
                    return
                }
            }
            textInstance.properties = params
            if (!textInstance.hasErrors() && textInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'text.label', default: 'Text'), textInstance.id])}"
                redirect(action: "show", id: textInstance.id)
            }
            else {
                render(view: "edit", model: [textInstance: textInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'text.label', default: 'Text'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def textInstance = Text.get(params.id)
        if (textInstance) {
            try {
                textInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'text.label', default: 'Text'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'text.label', default: 'Text'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'text.label', default: 'Text'), params.id])}"
            redirect(action: "list")
        }
    }
}
