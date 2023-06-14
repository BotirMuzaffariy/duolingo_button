package uz.lazydevv.duolingobutton.ui.components

import android.view.MotionEvent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import uz.lazydevv.duolingobutton.ui.theme.Green
import uz.lazydevv.duolingobutton.ui.theme.ShadowColor

private val shadowSize = 4.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DuolingoButton(
    modifier: Modifier,
    text: String,
    color: Color = Green,
    textColor: Color = Color.White,
    shadowColor: Color = ShadowColor,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (back, btn) = createRefs()

        var animatedY by remember { mutableStateOf(0.dp) }

        Spacer(
            modifier = Modifier
                .constrainAs(back) {
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints

                    start.linkTo(btn.start)
                    end.linkTo(btn.end)
                    top.linkTo(btn.top)
                    bottom.linkTo(btn.bottom)

                    translationY = shadowSize
                }
                .clip(RoundedCornerShape(12.dp))
                .background(shadowColor)
        )

        val myAnim by animateDpAsState(
            targetValue = animatedY,
            animationSpec = tween(50),
            label = ""
        )

        var btnSize by remember { mutableStateOf(IntSize.Zero) }

        Button(
            onClick = onClick,
            modifier = Modifier
                .constrainAs(btn) {
                    width = Dimension.matchParent
                    top.linkTo(parent.top)

                    translationY = myAnim
                }
                .border(
                    width = (1.2).dp,
                    color = shadowColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .indication(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                )
                .onGloballyPositioned {
                    btnSize = it.size
                }
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            animatedY = shadowSize
                        }

                        MotionEvent.ACTION_UP -> {
                            animatedY = 0.dp
                        }

                        MotionEvent.ACTION_MOVE -> {
                            val isOutside = it.x.toInt() !in 0..btnSize.width
                                    || it.y.toInt() !in 0..btnSize.height

                            if (isOutside) animatedY = 0.dp
                        }
                    }
                    true
                },
            contentPadding = PaddingValues(0.dp, 14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = textColor
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDuolingoButton() {
    DuolingoButton(
        modifier = Modifier,
        text = "Preview"
    ) {}
}