function submit() {
    const username = document.querySelector('#username').value;
    const password = document.querySelector('#password').value;
debugger;

    fetch('http://localhost:8081/login', {
        method: 'GET',
        body: JSON.stringify({
            username,
            password
        }),
        headers: {
            'Content-type': 'application/json; charset=UTF-8'
        }
    });
}