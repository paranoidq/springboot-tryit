package me.test.springboottryit.security;

/**
 * @author paranoidq
 * @since 1.0.0
 */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
////        // actuator端点安全配置
////        http.requestMatcher(EndpointRequest.toAnyEndpoint())
////            .authorizeRequests()
////            .anyRequest()
////            .permitAll()
////        ;
//
//        http.formLogin().and()
//            .authorizeRequests()
//            .antMatchers("/", "/home").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin().loginPage("/login").permitAll()
//            .and()
//            .logout().permitAll()
//            .and().csrf().disable()
//        ;
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("user")
//            // 为了安全考虑，这里应该直接写入做加密后的字符串，否则源码中也有密码明文存在
//            .password(passwordEncoder().encode("user"))
//            .roles("USER");
//    }
//
//    @Bean
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
//
//    /**
//     * Export一个{@link PasswordEncoder}实例Bean
//     * SpringSecurity要求必须提供PasswordEncoder实例，否则报错：There is no PasswordEncoder mapped for the id "null"
//     *
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
