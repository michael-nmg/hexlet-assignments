@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage build

@template.layout.page(
    content = @`
        <div class="container">
            @if (build.getErrors() != null)
                @for (var errors : build.getErrors().values())
                    @for(var error : errors)
                        <div class="alert alert-danger" role="alert">${error.getMessage()}</div>
                    @endfor
                @endfor
            @endif
        </div>

        <div class="container">
            <form action="/articles" method="POST">
                <div class="form-group">
                    <label for="title-article">Email address</label>
                    <input name="title" type="text" class="form-control" id="title-article"
                           placeholder="${build.getTitle()}" required>
                </div>

                <div class="form-group">
                    <label for="textarea">Example textarea</label>
                    <textarea name="content" class="form-control" id="textarea"
                              rows="10" placeholder="${build.getContent()}" required></textarea>
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    `
)
