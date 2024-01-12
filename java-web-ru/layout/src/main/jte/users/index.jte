@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    title = "All users",
    content = @`
        @if(page.getUsers().isEmpty())
            users is empty
        @else
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Firstname & Lastname</th>
                    <th scope="col">email</th>
                </tr>
                </thead>
                <tbody>
                @for (var user : page.getUsers())
                    <tr>
                        <th scope="row">${user.getId()}</th>
                        <td>${user.getFirstName()} ${user.getLastName()}</td>
                        <td>${user.getEmail()}</td>
                    </tr>
                @endfor
                </tbody>
            </table>
        @endif
    `
)
