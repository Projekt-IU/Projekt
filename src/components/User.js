class User {
    constructor() {
        this.userId = 0;
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.loggedIn = false;
        this.password = null;
    }


    saveToSession() {
        sessionStorage.setItem('user', JSON.stringify(this));
    }

    loadFromSession() {
        const storedData = sessionStorage.getItem('user');
        if (storedData) {
            const user = JSON.parse(storedData);
            this.userId = user.userId;
            this.username = user.username;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.loggedIn = user.loggedIn;
            this.password = user.password;
        }
    }

    login(id, username, firstName, lastName, password) {
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
        this.password = password;
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