@import exercise.dto.MainPage
@import exercise.util.NamedRoutes

@param MainPage page

@template.layout.page(
content = @`
    <div class="container">
        @if (page.getName() == null)
            <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
        @else
            <h6>${page.getName().toString()}</h6>
            <form action="${NamedRoutes.logoutPath()}" method="POST">
                <button type="submit" class="btn btn-danger btn-sm">Выйти</button>
            </form>
        @endif
    </div>
`
)
