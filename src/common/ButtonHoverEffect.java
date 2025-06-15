package common;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ButtonHoverEffect {
    private final ScaleTransition scaleIn;
    private final ScaleTransition scaleOut;

    public ButtonHoverEffect(Node targetNode) {
        // 初始化放大动画
        scaleIn = new ScaleTransition(Duration.millis(200), targetNode);
        scaleIn.setToX(1.1);
        scaleIn.setToY(1.1);

        // 初始化缩小动画
        scaleOut = new ScaleTransition(Duration.millis(200), targetNode);
        scaleOut.setToX(1.0);
        scaleOut.setToY(1.0);

        // 设置悬停事件
        targetNode.setOnMouseEntered(this::handleButtonHover);
        targetNode.setOnMouseExited(this::handleButtonExit);
    }

    private void handleButtonHover(MouseEvent event) {
        scaleOut.stop();
        scaleIn.play();
    }

    private void handleButtonExit(MouseEvent event) {
        scaleIn.stop();
        scaleOut.play();
    }
}
