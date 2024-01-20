@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
    content = @`
        <div class="container">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                </tr>
                </thead>
                <tbody>
                @for (var post : page.getPosts())
                    <tr>
                        <th scope="row">${post.getId()}</th>
                        <td>
                            <a href="${NamedRoutes.postsPath()}/${post.getId()}">${post.getName()}</a>
                        </td>
                    </tr>
                @endfor
                </tbody>
            </table>
        </div>
        <div class="container">
            <div class="row align-items-center">
                <div class="col">
                    <a href="${NamedRoutes.postsPath()}?page=${page.getPage() - 1}">назад</a>
                </div>
                <div class="col">
                    <p class="fs-5">${page.getPage()}</p>
                </div>
                <div class="col">
                    <a href="${NamedRoutes.postsPath()}?page=${page.getPage() + 1}">вперед</a>
                </div>
            </div>
        </div>
    `
)
