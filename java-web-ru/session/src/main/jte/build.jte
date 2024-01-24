@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes

@param LoginPage page

@template.layout.page(
    content = @`
        <div class="container">
            @if (page.getErrors() != null)
                @for (var errors : page.getErrors().values())
                    @for (var error : errors)
                        <div class="alert alert-danger" role="alert">
                            ${error.getMessage()}
                        </div>
                    @endfor
                @endfor
            @endif
        </div>

        <div class="container">
            <form action="${NamedRoutes.loginPath()}" method="POST">
                <div class="mb-3">
                    <label for="loginform" class="form-label">Login</label>
                    <input name="name" type="text" class="form-control" id="loginform" aria-describedby="emailHelp"
                           placeholder="login" required>
                </div>
                <div class="mb-3">
                    <label for="passform" class="form-label">Password</label>
                    <input name="password" type="password" class="form-control" id="passform"
                           placeholder="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    `
)
