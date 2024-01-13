@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
    <form action="/users" method="get">
        <input type="search" name="term" value="${page.getTerm()}" />
        <input type="submit" value="Filter" />
    </form>

    @if (page.getUsers() == null)
        <div class="container">
            <p>Not users</p>
        </div>
    @else
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">First- & LastName</th>
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
