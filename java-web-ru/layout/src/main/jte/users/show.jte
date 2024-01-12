@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    title = String.format("%s user", page.getUser().getId()),
    content = @`
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Firstname Lastname</th>
                <th scope="col">email</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">${page.getUser().getId()}</th>
                <td>${page.getUser().getFirstName()} ${page.getUser().getLastName()}</td>
                <td>${page.getUser().getEmail()}</td>
            </tr>
            </tbody>
        </table>
    `
)
