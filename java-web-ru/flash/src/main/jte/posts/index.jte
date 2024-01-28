@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">name</th>
            </tr>
            </thead>
            <tbody>
            @for (var post : page.getPosts())
                <tr>
                    <th scope="row">${post.getId()}</th>
                    <td>
                        <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
                    </td>
                </tr>
            @endfor
            </tbody>
        </table>
    </div>

    <div class="container">
        <a href="${NamedRoutes.buildPostPath()}">create new post</a>
    </div>
`
)
