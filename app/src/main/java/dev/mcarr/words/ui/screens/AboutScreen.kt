package dev.mcarr.words.ui.screens

import android.content.Intent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import dev.mcarr.words.BuildConfig
import dev.mcarr.words.R
import dev.mcarr.words.ui.components.Heading
import dev.mcarr.words.ui.components.NavCard
import dev.mcarr.words.ui.components.NavCardWithIcon
import dev.mcarr.words.ui.components.PaddedText
import dev.mcarr.words.ui.components.PreviewComponent

/**
 * Screen for displaying app information and relevant links.
 *
 * @param paddingValues Padding around the screen components
 * */
@Composable
fun AboutScreen(
    paddingValues: PaddingValues
) {

    val c = LocalContext.current
    val darkMode = isSystemInDarkTheme()

    val githubIcon =
        if (darkMode)
            ImageVector.vectorResource(R.drawable.github_dark)
        else
            ImageVector.vectorResource(R.drawable.github_white)

    val appName = remember {
        c.getString(R.string.app_name)
    }

    val githubRepoUrl = remember {
        c.getString(R.string.github_repo_url)
    }

    val githubReportIssueUrl = remember {
        c.getString(R.string.github_report_issue_url)
    }

    val openUrl: (url: String) -> Unit = { url ->
        val uri = url.toUri()
        val i = Intent(Intent.ACTION_VIEW, uri)
        c.startActivity(i)
    }

    Column(
        modifier = Modifier.padding(paddingValues)
    ) {

        Heading(text = appName)

        PaddedText(text = "Version ${BuildConfig.VERSION_NAME}")

        PaddedText(text = "Developed by mcarr823")
        PaddedText(text = "Github icon provided by Github")
        PaddedText(text = "Other icons provided by Google under Apache License Version 2.0")
        PaddedText(text = "Default word list provided by Heroku App")
        PaddedText(text = "See the Github page for up to date information on licensing, credits, etc.")

        Spacer(modifier = Modifier.height(8.dp))

        NavCardWithIcon(
            icon = githubIcon,
            text = "Source Code",
            subtext = githubRepoUrl,
            onClick = { openUrl(githubRepoUrl) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        NavCardWithIcon(
            icon = githubIcon,
            text = "Report Issue or Request Feature",
            subtext = githubReportIssueUrl,
            onClick = { openUrl(githubReportIssueUrl) }
        )

    }

}

@Preview
@Composable
fun PreviewAboutScreen(){
    PreviewComponent {
        AboutScreen(
            paddingValues = PaddingValues(0.dp)
        )
    }
}

@Preview
@Composable
fun PreviewAboutScreenDark(){
    PreviewComponent(
        darkMode = true
    ) {
        AboutScreen(
            paddingValues = PaddingValues(0.dp)
        )
    }
}