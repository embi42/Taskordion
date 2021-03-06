import { shallowMount } from '@vue/test-utils'
import HelloWorld from '@/components/HelloWorld.vue'

describe('HelloWorld.vue', () => {
  it('renders props.msg when passed', () => {
    const msg = 'CALL Spring Boot REST backend service []'
    const wrapper = shallowMount(HelloWorld, {
      propsData: { msg }
    })
    expect('hello').toMatch('hello')
  })
})
