@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
    content = @`
        <div class="container">
            <h3>${page.getPost().getName()}</h3>
            <p class="text-break lh-sm fs-6 fw-normal">${page.getPost().getBody()}</p>
        </div>
        <div class="container">
            <a href="${NamedRoutes.postsPath()}">main</a>
        </div>
    `
)
