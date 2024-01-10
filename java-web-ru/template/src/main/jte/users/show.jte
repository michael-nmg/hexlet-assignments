@import exercise.dto.users.UserPage
@param UserPage user

<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>user</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <div>
            <a href="/users">Пользователи</a>
        </div>
        <div>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Firstname</th>
                    <th scope="col">Lastname</th>
                    <th scope="col">email</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">${user.getUser().getId()}</th>
                    <td>${user.getUser().getFirstName()}</td>
                    <td>${user.getUser().getLastName()}</td>
                    <td>${user.getUser().getEmail()}</td>
                </tr>
                </tbody>
            </table>
        </div>

    </main>
</div>
</body>
</html>
