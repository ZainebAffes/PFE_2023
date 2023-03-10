var EditorDate = Backgrid.InputCellEditor.extend({
    attributes: {
        type: "text",
        style: "width:90%;border: 1px solid #3498db;"
    },
    validate: function (newValue) {
        return true;
    },
    nextcell: true,
    selectOnFocus: false,
    saveOrCancel: function (e) {

        var formatter = this.formatter;
        var model = this.model;
        var column = this.column;
        var command = new Backgrid.Command(e);
        var blurred = e.type === "blur";
        if (command.moveUp() || command.moveDown() || command.moveLeft() || command.moveRight() ||
                command.save() || blurred) {

            e.preventDefault();
            e.stopPropagation();
            var val = this.$el.val();
            var newValue = formatter.toRaw(val, model);
            if (_.isUndefined(newValue) || !(this.validate(newValue))) {
                model.trigger("backgrid:error", model, column, val);
            } else {
                if (command.save() && this.nextcell)
                    command.keyCode = 9;
                model.set(column.get("name"), newValue, {validate: true});
                model.trigger("backgrid:edited", model, column, command);
            }
        }
        // esc
        else if (command.cancel()) {
            // undo
            e.stopPropagation();
            model.trigger("backgrid:edited", model, column, command);
        }

    },
    postRender: function (model, column) {
        if (column === null || column.get("name") === this.column.get("name")) {
            // move the cursor to the end on firefox if text is right aligned
            if (this.$el.css("text-align") === "right") {
                var val = this.$el.val();
                if (this.selectOnFocus)
                    this.$el.focus().val(null).val(val).select().mask("99/99/9999");
                else
                    this.$el.focus().val(null).val(val).mask("99/99/9999");
            } else
            if (this.selectOnFocus)
                this.$el.focus().select().mask("99/99/9999");
            else
                this.$el.focus().mask("99/99/9999");
        }
        return this;
    }

});
var EditorString = Backgrid.InputCellEditor.extend({
    attributes: {
        type: "text",
        style: "width:90%;    border: 1px solid #3498db;" // ferou9
    },
    validate: function (newValue) {
        return true;
    },
    nextcell: true,
    selectOnFocus: true,
    saveOrCancel: function (e) {

        var formatter = this.formatter;
        var model = this.model;
        var column = this.column;
        var command = new Backgrid.Command(e);
        var blurred = e.type === "blur";
        if (command.moveUp() || command.moveDown() || command.moveLeft() || command.moveRight() ||
                command.save() || blurred) {
            e.preventDefault();
            e.stopPropagation();
            var val = this.$el.val();
            var newValue = formatter.toRaw(val, model);
            if (_.isUndefined(newValue) || !(this.validate(newValue))) {
                model.trigger("backgrid:error", model, column, val);
            } else {
                if (command.save() && this.nextcell)
                    command.keyCode = 9;
                model.set(column.get("name"), newValue, {validate: true});
                model.trigger("backgrid:edited", model, column, command);
            }
        }
        // esc
        else if (command.cancel()) {
            // undo
            e.stopPropagation();
            model.trigger("backgrid:edited", model, column, command);
        }

    },
    postRender: function (model, column) {
        if (column === null || column.get("name") === this.column.get("name")) {
            // move the cursor to the end on firefox if text is right aligned
            if (this.$el.css("text-align") === "right") {
                var val = this.$el.val();
                if (this.selectOnFocus)
                    this.$el.focus().val(null).val(val).select();
                else
                    this.$el.focus().val(null).val(val);
            } else
            if (this.selectOnFocus)
                this.$el.focus().select();
            else
                this.$el.focus();
        }
        return this;
    }

});
var EditorCodePostal = Backgrid.InputCellEditor.extend({
    attributes: {
        type: "text",
        style: "width:90%;    border: 1px solid #3498db;" // ferou9
    },
    validate: function (newValue) {
        return true;
    },
    nextcell: true,
    selectOnFocus: true,
    saveOrCancel: function (e) {

        var formatter = this.formatter;
        var model = this.model;
        var column = this.column;
        var command = new Backgrid.Command(e);
        var blurred = e.type === "blur";
        if (command.moveUp() || command.moveDown() || command.moveLeft() || command.moveRight() ||
                command.save() || blurred) {
            e.preventDefault();
            e.stopPropagation();
            var val = this.$el.val();
            var newValue = formatter.toRaw(val, model);
            if (_.isUndefined(newValue) || !(this.validate(newValue))) {
                model.trigger("backgrid:error", model, column, val);
            } else {
                if (command.save() && this.nextcell)
                    command.keyCode = 9;
                model.set(column.get("name"), newValue, {validate: true});
                model.trigger("backgrid:edited", model, column, command);
            }
        }
        // esc
        else if (command.cancel()) {
            // undo
            e.stopPropagation();
            model.trigger("backgrid:edited", model, column, command);
        }

    },
    postRender: function (model, column) {
        if (column === null || column.get("name") === this.column.get("name")) {
            // move the cursor to the end on firefox if text is right aligned
            if (this.$el.css("text-align") === "right") {
                var val = this.$el.val();
                if (this.selectOnFocus)
                    this.$el.focus().val(null).val(val).select();
                else
                    this.$el.focus().val(null).val(val);
            } else
            if (this.selectOnFocus)
                this.$el.focus().select();
            else
                this.$el.focus();
        }
        return this;
    }

});
var codePostalformatter = {
    fromRaw: function (rawData, model) {
        return rawData;
    },
    toRaw: function (formattedData, model) {
        var villes = findSouRegByCp(formattedData);
        if (villes.length > 0) {

            model.set("codregion", villes[0].codregion);
            model.set("desregion", villes[0].desregion);
            model.set("codsoureg", villes[0].codsoureg);
            model.set("dessoureg", villes[0].dessoureg);
            if ((_.filter(model.collection.models, function (num) {
                return num.get('codregion') === null;
            }).length) < 1) {
                pageTableListAdresse.collection.add({codregion: null});
            }
            return formattedData;
        } else {
            showNotification('Avertissement', "Le code postal est erroné !", 'error', 3000);
            return undefined;
        }
    }

};
var EditorNumber = Backgrid.InputCellEditor.extend({
    attributes: {
        type: "text",
        style: "width:90%;border: 1px solid #3498db;text-align:right;"  // ferou9
    },
    validate: function (newValue) {
        return true;
    },
    nextcell: true,
    selectOnFocus: true,
    saveOrCancel: function (e) {

        var formatter = this.formatter;
        var model = this.model;
        var column = this.column;
        var command = new Backgrid.Command(e);
        var blurred = e.type === "blur";
        if (command.moveUp() || command.moveDown() || command.moveLeft() || command.moveRight() ||
                command.save() || blurred) {
            e.preventDefault();
            e.stopPropagation();
            var val = this.$el.val();
            var newValue = formatter.toRaw(val, model);
            if (_.isUndefined(newValue) || !(this.validate(newValue))) {
                model.trigger("backgrid:error", model, column, val);
            } else {
                if (command.save() && this.nextcell)
                    command.keyCode = 9;
                model.set(column.get("name"), newValue, {validate: true});
                model.trigger("backgrid:edited", model, column, command);
            }
        }
        // esc
        else if (command.cancel()) {
            // undo
            e.stopPropagation();
            model.trigger("backgrid:edited", model, column, command);
        }

    },
    postRender: function (model, column) {
        if (column === null || column.get("name") === this.column.get("name")) {
            // move the cursor to the end on firefox if text is right aligned
            if (this.$el.css("text-align") === "right") {
                var val = this.$el.val();
                if (this.selectOnFocus)
                    this.$el.focus().val(null).val(val.replaceAll(' ', '')).select();
                else
                    this.$el.focus().val(null).val(val.replaceAll(' ', ''));
            } else
            if (this.selectOnFocus)
                this.$el.focus().select();
            else
                this.$el.focus();
        }
        return this;
    }
});