package main.canny

enum class KernelType(val kernelMatrix: IntArray) {

    /**
     * The identity matrix. Has no effect on the imagery
     */
    IDENTITY(
        intArrayOf(
            0, 0, 0,
            0, 1, 0,
            0, 0, 0
        )
    ),

    /**
     * Edge detection 1
     */
    EDGE_DETECTION_1(
        intArrayOf(
            1, 0, -1,
            0, 0, 0,
            -1, 0, 1
        )
    ),

    /**
     * Edge detection 2
     */
    EDGE_DETECTION_2(
        intArrayOf(
            0, 1, 0,
            1, -4, 1
            , 0, 1, 0
        )
    ),

    /**
     * Edge detection 3
     */
    EDGE_DETECTION_3(
        intArrayOf(
            -1, -1, -1,
            -1, 8, -1,
            -1, -1, 1
        )
    ),

    /**
     * My own edge detection matrix.
     */
    EDGE_DETECTION_4(
        intArrayOf(
            1, 2, 0,
            0, 4, 0,
            0, -2, -1
        )
    ),

    /**
     * Sharpen matrix
     */
    SHARPEN(
        intArrayOf(
            0, -1, 0,
            -1, 5, -1,
            0, -1, 0
        )
    )
}