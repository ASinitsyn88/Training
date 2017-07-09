function formValidation() {
    var usersForm = new UsersForm();
    emptyFieldValidation(usersForm);
}

function UsersForm() {
    this.lastnameInput = document.getElementById('lastname');
    this.firstnameInput = document.getElementById('firstname');
    this.patronymicInput = document.getElementById('patronymic');
    this.phoneInput = document.getElementById('phone');
    this.loginInput = document.getElementById('login');
}

function emptyFieldValidation(usersForm) {
    if (isEmpty(usersForm.lastnameInput.value)) {
        alert("lastname is empty");
    }
    if (isEmpty(usersForm.firstnameInput.value)) {
        alert("name is empty");
    }
    if (isEmpty(usersForm.phoneInput.value)) {
        alert("phone is empty");
    }
    if (isEmpty(usersForm.loginInput.value)) {
        alert("login is empty");
    }
}

function isEmpty(value) {
    return value.length === 0 || !value.trim() || typeof value == "undefined";
}
