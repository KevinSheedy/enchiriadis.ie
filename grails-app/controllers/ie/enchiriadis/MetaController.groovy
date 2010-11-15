package ie.enchiriadis

class MetaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [metaInstanceList: Meta.list(params), metaInstanceTotal: Meta.count()]
    }

    def create = {
        def metaInstance = new Meta()
        metaInstance.properties = params
        return [metaInstance: metaInstance]
    }

    def save = {
        def metaInstance = new Meta(params)
        if (metaInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'meta.label', default: 'Meta'), metaInstance.id])}"
            redirect(action: "show", id: metaInstance.id)
        }
        else {
            render(view: "create", model: [metaInstance: metaInstance])
        }
    }

    def show = {
        def metaInstance = Meta.get(params.id)
        if (!metaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'meta.label', default: 'Meta'), params.id])}"
            redirect(action: "list")
        }
        else {
            [metaInstance: metaInstance]
        }
    }

    def edit = {
        def metaInstance = Meta.get(params.id)
        if (!metaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'meta.label', default: 'Meta'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [metaInstance: metaInstance]
        }
    }

    def update = {
        def metaInstance = Meta.get(params.id)
        if (metaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (metaInstance.version > version) {
                    
                    metaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'meta.label', default: 'Meta')] as Object[], "Another user has updated this Meta while you were editing")
                    render(view: "edit", model: [metaInstance: metaInstance])
                    return
                }
            }
            metaInstance.properties = params
            if (!metaInstance.hasErrors() && metaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'meta.label', default: 'Meta'), metaInstance.id])}"
                redirect(action: "show", id: metaInstance.id)
            }
            else {
                render(view: "edit", model: [metaInstance: metaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'meta.label', default: 'Meta'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def metaInstance = Meta.get(params.id)
        if (metaInstance) {
            try {
                metaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'meta.label', default: 'Meta'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'meta.label', default: 'Meta'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'meta.label', default: 'Meta'), params.id])}"
            redirect(action: "list")
        }
    }
}
