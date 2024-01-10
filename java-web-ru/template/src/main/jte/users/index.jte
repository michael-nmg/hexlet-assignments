@import exercise.dto.users.UsersPage
@import exercise.model.User
@param UsersPage users

<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <title>All Users</title>
</head>
<body>
<div class="mx-auto p-4 py-md-5">
    <main>
        <div>
            <a href="/users">Пользователи</a>
        </div>
        <div>
            @if(users.getUsers().isEmpty())
                users is empty
            @else
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
                    @for (User user : users.getUsers())
                        <tr>
                            <th scope="row">${user.getId()}</th>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getEmail()}</td>
                        </tr>
                    @endfor
                    </tbody>
                </table>
            @endif
        </div>
    </main>
</div>
</body>
</html>
