<template>
  <div
    class="word-cloud-container"
    :style="{ ...curStyle?.wrapper?.default }"
  >
    <!-- 图表容器 -->
    <div
      ref="chart"
      class="word-cloud"
    />
  </div>
</template>
 
<style lang="scss">
.word-cloud-container {
  .word-cloud {
    width: 100%;
    height: 100%;
  }
}
</style>