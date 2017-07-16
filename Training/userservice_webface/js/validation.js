function formValidation() {
    var usersForm = new UsersForm();
    return emptyFieldValidation(usersForm);
}

function UsersForm() {
    this.lastnameInput = document.getElementById('lastname');
    this.firstnameInput = document.getElementById('firstname');
    this.patronymicInput = document.getElementById('patronymic');
    this.phoneInput = document.getElementById('phone');
    this.loginInput = document.getElementById('login');
}

function emptyFieldValidation(usersForm) {
    var isValid = true;
    if (isEmpty(usersForm.lastnameInput.value)) {
        showError(usersForm.lastnameInput);
        isValid = false;
    }
    if (isEmpty(usersForm.firstnameInput.value)) {
        showError(usersForm.firstnameInput);
        isValid = false;
    }
    if (isEmpty(usersForm.phoneInput.value)) {
        showError(usersForm.phoneInput);
        isValid = false;
    }
    if (isEmpty(usersForm.loginInput.value)) {
        showError(usersForm.loginInput);
        isValid = false;
    }
    return isValid;
}

function isEmpty(value) {
    return value.length === 0 || !value.trim() || typeof value == "undefined";
}

function showError(input) {
    document.getElementById(input.id + "Error").innerHTML = "Поле должно быть заполнено";
}
