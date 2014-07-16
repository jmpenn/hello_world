#!/usr/bin/ruby

if ARGV.count != 0
    ARGV.each do |recipient|
    puts "Hello #{recipient.capitalize}!"
    end
else
    puts "Hello World!"
end

