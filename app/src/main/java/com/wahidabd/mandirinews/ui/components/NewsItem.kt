package com.wahidabd.mandirinews.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.wahidabd.mandirinews.R
import com.wahidabd.mandirinews.domain.News
import com.wahidabd.mandirinews.domain.Source
import com.wahidabd.mandirinews.ui.theme.Shapes
import com.wahidabd.mandirinews.utils.formatStringToDate


/**
 * Created by Wahid on 6/28/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun NewsItem(
    data: News,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.White, shape = RoundedCornerShape(6.dp))
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(6.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(width = 140.dp, height = 120.dp)
                .clip(shape = RoundedCornerShape(6.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data.urlToImage)
                .crossfade(true)
                .build(),
            error = painterResource(id = R.drawable.image_not_available),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.image_not_available),
            contentDescription = null,
        )

        Column(
            modifier = Modifier
                .weight(2f, false)
                .padding(horizontal = 10.dp, vertical = 4.dp),
        ) {

            Text(
                text = data.title.toString(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyLarge,
            )

            TextIconRow(
                title = data.author ?: "No Data",
                icon = Icons.Outlined.Person,
                modifier = Modifier.padding(top = 16.dp)
            )
            TextIconRow(
                title = data.publishedAt?.formatStringToDate() ?: "No Data",
                icon = Icons.Outlined.DateRange,
                modifier = Modifier.padding(top = 4.dp)
            )

        }
    }
}

@Composable
fun TextIconRow(
    title: String = "Mandiri",
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    val data = News(
        source = Source(id = "Mandiri", "Mandiri News"),
        author = "Abd. Wahid",
        content = "Lorem ipsum dolor is amet",
        description = "Lorem ipsum dolor is amet",
        publishedAt = "2023-06-27T16:36:54Z",
        title = "Gebrakan baru aplikasi Livin by Mandiri",
        url = "this is url",
        urlToImage = "image to url"
    )
    NewsItem(data = data, onClick = {})
}