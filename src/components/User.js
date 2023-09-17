class User {
    constructor() {
        this.userId = 0;
        this.username = null;
        this.firstName = null;
        this.lastName = null;
        this.loggedIn = false;
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