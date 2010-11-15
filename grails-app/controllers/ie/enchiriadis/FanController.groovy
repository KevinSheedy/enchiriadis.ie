package ie.enchiriadis

class FanController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [fanInstanceList: Fan.list(params), fanInstanceTotal: Fan.count()]
    }

    def create = {
        def fanInstance = new Fan()
        fanInstance.properties = params
        return [fanInstance: fanInstance]
    }

    def save = {
        def fanInstance = new Fan(params)
        if (fanInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'fan.label', default: 'Fan'), fanInstance.id])}"
            redirect(action: "show", id: fanInstance.id)
        }
        else {
            render(view: "create", model: [fanInstance: fanInstance])
        }
    }

    def show = {
        def fanInstance = Fan.get(params.id)
        if (!fanInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fan.label', default: 'Fan'), params.id])}"
            redirect(action: "list")
        }
        else {
            [fanInstance: fanInstance]
        }
    }

    def edit = {
        def fanInstance = Fan.get(params.id)
        if (!fanInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fan.label', default: 'Fan'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [fanInstance: fanInstance]
        }
    }

    def update = {
        def fanInstance = Fan.get(params.id)
        if (fanInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (fanInstance.version > version) {
                    
                    fanInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'fan.label', default: 'Fan')] as Object[], "Another user has updated this Fan while you were editing")
                    render(view: "edit", model: [fanInstance: fanInstance])
                    return
                }
            }
            fanInstance.properties = params
            if (!fanInstance.hasErrors() && fanInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'fan.label', default: 'Fan'), fanInstance.id])}"
                redirect(action: "show", id: fanInstance.id)
            }
            else {
                render(view: "edit", model: [fanInstance: fanInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fan.label', default: 'Fan'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def fanInstance = Fan.get(params.id)
        if (fanInstance) {
            try {
                fanInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'fan.label', default: 'Fan'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'fan.label', default: 'Fan'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'fan.label', default: 'Fan'), params.id])}"
            redirect(action: "list")
        }
    }
}
