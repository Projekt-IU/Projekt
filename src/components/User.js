class User {
    constructor() {
        this.userId = 0;
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.loggedIn = false;
    }

    register(username, firstName, lastName, courseOfStudy, email, password, repeatPassword) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseOfStudy = courseOfStudy;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.registered = true;
    }

    login(id, username, firstName, lastName) {
        this.userId = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loggedIn = true;
    }

    logout() {
        this.userId = null;
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.loggedIn = false;
    }
}

// eslint-disable-next-line import/no-anonymous-default-export
export default new User();